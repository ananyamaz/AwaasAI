from fastapi import FastAPI
from pydantic import BaseModel

from app.rag_repository import get_all_rag_chunks
from app.retrieval.embedder import create_embedding
from app.retrieval.retriever import retrieve_top_chunks
from app.generator import generate_answer


app = FastAPI(
    title="AwaasAI RAG Service",
    description="Retrieval-Augmented Generation service for AwaasAI",
    version="1.0.0"
)




class RAGQuery(BaseModel):
    question: str


@app.get("/health")
def health_check():
    return {
        "status": "healthy",
        "service": "awaasai-rag-service"
    }


@app.post("/api/rag/query")
def rag_query(request: RAGQuery):

    question = request.question.strip()

    if not question:
        return {
            "error": "Question cannot be empty"
        }

    # 1. Create embedding for the user's question
    query_embedding = create_embedding(question)

    # 2. Retrieve chunks from MySQL
    chunks = get_all_rag_chunks()

    # 3. Rank chunks using cosine similarity
    retrieved_chunks = retrieve_top_chunks(
        query_embedding,
        chunks,
        top_k=3
    )

    # 4. Generate grounded answer using Ollama
    result = generate_answer(
        question,
        retrieved_chunks
    )

    return {
        "question": question,
        "answer": result["answer"],
        "sources": result["sources"]
    }