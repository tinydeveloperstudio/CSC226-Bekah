package Week11;

import week5.QueueInterface;

public interface WeightedGraphInterface<T> {
    int NULL_EDGE = -1;

    boolean isEmpty();
    boolean isFull();
    void addVertex(T vertex);
    boolean hasVertex(T vertex);
    void addEdge(T fromVertex, T toVertex, int weight);
    int weightIs(T fromVertex, T toVertex);
    QueueInterface<T> getToVertices(T vertex);

    void clearMarks();
    void markVertex(T vertex);
    boolean isMarked(T vertex);
    T getUnmarked();
}
