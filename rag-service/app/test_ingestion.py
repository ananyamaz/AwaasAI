from app.ingestion_service import ingest_document


document_path = "data/documents/construction_guidelines.txt"

document_id = ingest_document(document_path)

print("Construction document ingestion successful!")
print(f"Document ID: {document_id}")