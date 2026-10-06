import numpy as np


def chunk_text(text: str) -> list[str]:
    """
    Split a document into section-based chunks.

    Each heading becomes the beginning of a chunk.
    """

    if not text:
        return []

    sections = text.split("\n\n")

    chunks = []
    current_chunk = ""

    for section in sections:
        section = section.strip()

        if not section:
            continue

        if current_chunk:
            chunks.append(current_chunk)

        current_chunk = section

    if current_chunk:
        chunks.append(current_chunk)

    return chunks


def cosine_similarity(
    vector_a: list[float],
    vector_b: list[float]
) -> float:
    """
    Calculate cosine similarity between two vectors.
    """

    a = np.array(vector_a)
    b = np.array(vector_b)

    denominator = np.linalg.norm(a) * np.linalg.norm(b)

    if denominator == 0:
        return 0.0

    return float(np.dot(a, b) / denominator)


def retrieve_top_chunks(
    query_embedding: list[float],
    chunks: list[dict],
    top_k: int = 3
) -> list[dict]:
    """
    Rank document chunks by cosine similarity to the query.
    """

    results = []

    for chunk in chunks:
        similarity = cosine_similarity(
            query_embedding,
            chunk["embedding"]
        )

        results.append({
            "chunk": chunk["chunk_text"],
            "similarity": similarity,
            "document_id": chunk["document_id"],
            "file_name": chunk["file_name"],
            "title": chunk["title"],
            "version": chunk["version"],
            "chunk_index": chunk["chunk_index"]
        })

    results.sort(
        key=lambda x: x["similarity"],
        reverse=True
    )

    return results[:top_k]