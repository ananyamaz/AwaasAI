from pathlib import Path

from app.ingestion.loader import load_text_file
from app.retrieval.retriever import chunk_text
from app.retrieval.embedder import create_embedding
from app.rag_repository import insert_document, insert_chunk


def ingest_document(file_path: str) -> int:
    """
    Load a document, split it into chunks,
    generate embeddings, and store everything in MySQL.
    """

    path = Path(file_path)

    # 1. Load document
    content = load_text_file(file_path)

    # 2. Create document record
    # Use the first non-empty line of the document as its title
    document_title = next(
        line.strip()
        for line in content.splitlines()
        if line.strip()
    )

    document_id = insert_document(
        file_name=path.name,
        title=document_title,
        version="1.0",
        source_type="reference"
    )

    # 3. Split document into chunks
    chunks = chunk_text(content)

    # 4. Generate embeddings and store chunks
    for index, chunk in enumerate(chunks):

        embedding = create_embedding(chunk)

        insert_chunk(
            document_id=document_id,
            chunk_index=index,
            chunk_text=chunk,
            embedding=embedding
        )

    return document_id