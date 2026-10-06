from app.rag_repository import insert_document


document_id = insert_document(
    file_name="housing_finance_guide.txt",
    title="AwaasAI Housing Finance Guide",
    version="1.0",
    source_type="reference"
)

print(f"Document inserted successfully. ID: {document_id}")