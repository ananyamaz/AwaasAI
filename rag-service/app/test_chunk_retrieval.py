from app.rag_repository import get_all_chunks


chunks = get_all_chunks(document_id=2)

print(f"Chunks retrieved: {len(chunks)}")

for chunk in chunks:
    print(
        f"Chunk {chunk['chunk_index']} | "
        f"Embedding dimensions: {len(chunk['embedding'])}"
    )