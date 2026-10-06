from app.rag_repository import get_all_chunks
from app.retrieval.embedder import create_embedding
from app.retrieval.retriever import retrieve_top_chunks
from app.generator import generate_answer


DOCUMENT_ID = 4

question = "What documents are needed for income verification?"

# 1. Create embedding for the question
query_embedding = create_embedding(question)

# 2. Load chunks from MySQL
chunks = get_all_chunks(DOCUMENT_ID)

# 3. Retrieve the most relevant chunks
results = retrieve_top_chunks(
    query_embedding,
    chunks,
    top_k=3
)

# 4. Generate an answer using the retrieved context
answer = generate_answer(
    question,
    results
)

print()
print("Question:")
print(question)

print()
print("Answer:")
print(answer["answer"])

print()
print("Sources:")

for index, source in enumerate(answer["sources"], start=1):
    print(
        f"{index}. {source['title']} "
        f"(similarity: {source['similarity']})"
    )
print()
print("Retrieved Sources:")

for index, result in enumerate(results, start=1):
    print(
        f"{index}. Similarity: "
        f"{result['similarity']:.4f}"
    )
    print(result["chunk"][:300])
    print("-" * 60)