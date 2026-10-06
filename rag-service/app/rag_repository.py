import json

from app.database import get_connection


def insert_document(
    file_name: str,
    title: str,
    version: str,
    source_type: str = "reference"
) -> int:
    """
    Insert a RAG document into MySQL
    and return its generated ID.
    """

    connection = get_connection()

    try:
        cursor = connection.cursor()

        query = """
            INSERT INTO rag_documents
            (file_name, title, version, source_type)
            VALUES (%s, %s, %s, %s)
        """

        cursor.execute(
            query,
            (file_name, title, version, source_type)
        )

        connection.commit()

        return cursor.lastrowid

    finally:
        cursor.close()
        connection.close()


def insert_chunk(
    document_id: int,
    chunk_index: int,
    chunk_text: str,
    embedding: list[float],
    page_number: int | None = None
) -> int:
    """
    Insert a document chunk and its embedding into MySQL.
    """

    connection = get_connection()

    try:
        cursor = connection.cursor()

        query = """
            INSERT INTO rag_chunks
            (document_id, chunk_index, chunk_text, embedding, page_number)
            VALUES (%s, %s, %s, %s, %s)
        """

        cursor.execute(
            query,
            (
                document_id,
                chunk_index,
                chunk_text,
                json.dumps(embedding),
                page_number
            )
        )

        connection.commit()

        return cursor.lastrowid

    finally:
        cursor.close()
        connection.close()

def get_all_chunks(document_id: int) -> list[dict]:
    """
    Retrieve all stored chunks and embeddings
    for a document from MySQL.
    """

    connection = get_connection()

    try:
        cursor = connection.cursor(dictionary=True)

        query = """
            SELECT
                rc.id,
                rc.document_id,
                rc.chunk_index,
                rc.chunk_text,
                rc.embedding,
                rc.page_number,
                rd.file_name,
                rd.title,
                rd.version
            FROM rag_chunks rc
            JOIN rag_documents rd
                ON rc.document_id = rd.id
            WHERE rc.document_id = %s
            ORDER BY rc.chunk_index
        """

        cursor.execute(query, (document_id,))

        rows = cursor.fetchall()

        for row in rows:
            row["embedding"] = json.loads(row["embedding"])

        return rows

    finally:
        cursor.close()
        connection.close()

def get_all_rag_chunks() -> list[dict]:
    """
    Retrieve all RAG chunks from all stored documents.
    """

    connection = get_connection()

    try:
        cursor = connection.cursor(dictionary=True)

        query = """
            SELECT
                rc.id,
                rc.document_id,
                rc.chunk_index,
                rc.chunk_text,
                rc.embedding,
                rc.page_number,
                rd.file_name,
                rd.title,
                rd.version
            FROM rag_chunks rc
            JOIN rag_documents rd
                ON rc.document_id = rd.id
            ORDER BY rc.document_id, rc.chunk_index
        """

        cursor.execute(query)

        rows = cursor.fetchall()

        for row in rows:
            row["embedding"] = json.loads(row["embedding"])

        return rows

    finally:
        cursor.close()
        connection.close()