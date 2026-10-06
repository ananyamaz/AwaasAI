from app.rag_repository import insert_chunk
from app.retrieval.embedder import create_embedding


text = """
Applicants may need to provide documents that demonstrate their income.
For salaried applicants, this may include salary slips, bank statements,
or other employment-related income evidence.
"""

embedding = create_embedding(text)

chunk_id = insert_chunk(
    document_id=1,
    chunk_index=0,
    chunk_text=text,
    embedding=embedding
)

print(f"Chunk inserted successfully. ID: {chunk_id}")
print(f"Embedding dimensions: {len(embedding)}")