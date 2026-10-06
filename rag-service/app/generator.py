import requests


OLLAMA_URL = "http://localhost:11434/api/generate"
MODEL_NAME = "llama3.2:3b"


import requests


OLLAMA_URL = "http://localhost:11434/api/generate"
MODEL_NAME = "llama3.2:3b"


def generate_answer(
    question: str,
    retrieved_chunks: list[dict]
) -> dict:

    context = "\n\n".join(
        [
            f"Source {index + 1}:\n{chunk['chunk']}"
            for index, chunk in enumerate(retrieved_chunks)
        ]
    )

    prompt = f"""
You are AwaasAI, a housing finance assistance system.

Answer the user's question using ONLY the information provided
in the context below.

STRICT RULES:
1. Do not use outside knowledge.
2. Do not invent documents, requirements, policies, or facts.
3. If the context does not contain enough information, say:
   "The available AwaasAI documents do not provide enough information
   to answer this question."
4. Do not mention "Source 1", "Source 2", etc. in your answer.
5. Keep the answer concise and easy to understand.

User question:
{question}

Context:
{context}

Answer:
"""

    response = requests.post(
        OLLAMA_URL,
        json={
            "model": MODEL_NAME,
            "prompt": prompt,
            "stream": False
        },
        timeout=120
    )

    response.raise_for_status()

    data = response.json()

    sources = []

    for chunk in retrieved_chunks:
        sources.append({
            "title": chunk["title"],
            "file_name": chunk["file_name"],
            "version": chunk["version"],
            "chunk_index": chunk["chunk_index"],
            "similarity": round(chunk["similarity"], 4)
        })

    return {
        "answer": data["response"].strip(),
        "sources": sources
    }