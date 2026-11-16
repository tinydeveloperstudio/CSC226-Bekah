package Week11;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import week5.QueueInterface;

public class WeightedGraph<T> implements WeightedGraphInterface<T> {
    private final int maxVertices;
    private final Map<T, Map<T, Integer>> edges = new LinkedHashMap<>();
    private final Set<T> markedVertices = new LinkedHashSet<>();

    public WeightedGraph(int maxVertices) {
        if (maxVertices <= 0) {
            throw new IllegalArgumentException("maxVertices must be positive");
        }
        this.maxVertices = maxVertices;
    }

    @Override
    public boolean isEmpty() {
        return edges.isEmpty();
    }

    @Override
    public boolean isFull() {
        return edges.size() == maxVertices;
    }

    @Override
    public void addVertex(T vertex) {
        Objects.requireNonNull(vertex, "vertex must not be null");
        if (hasVertex(vertex)) {
            throw new IllegalArgumentException("vertex already exists: " + vertex);
        }
        if (isFull()) {
            throw new IllegalStateException("graph is full");
        }
        edges.put(vertex, new LinkedHashMap<>());
    }

    @Override
    public boolean hasVertex(T vertex) {
        return edges.containsKey(vertex);
    }

    @Override
    public void addEdge(T fromVertex, T toVertex, int weight) {
        requireVertex(fromVertex);
        requireVertex(toVertex);
        if (weight == NULL_EDGE) {
            throw new IllegalArgumentException("weight cannot equal NULL_EDGE");
        }
        edges.get(fromVertex).put(toVertex, weight);
    }

    @Override
    public int weightIs(T fromVertex, T toVertex) {
        Map<T, Integer> destinations = edges.get(fromVertex);
        if (destinations == null) {
            return NULL_EDGE;
        }
        return destinations.getOrDefault(toVertex, NULL_EDGE);
    }

    @Override
    public QueueInterface<T> getToVertices(T vertex) {
        requireVertex(vertex);
        VertexQueue<T> destinations = new VertexQueue<>();
        for (T destination : edges.get(vertex).keySet()) {
            destinations.enqueue(destination);
        }
        return destinations;
    }

    @Override
    public void clearMarks() {
        markedVertices.clear();
    }

    @Override
    public void markVertex(T vertex) {
        requireVertex(vertex);
        markedVertices.add(vertex);
    }

    @Override
    public boolean isMarked(T vertex) {
        return markedVertices.contains(vertex);
    }

    @Override
    public T getUnmarked() {
        for (T vertex : edges.keySet()) {
            if (!isMarked(vertex)) {
                return vertex;
            }
        }
        return null;
    }

    private void requireVertex(T vertex) {
        if (!hasVertex(vertex)) {
            throw new IllegalArgumentException("vertex does not exist: " + vertex);
        }
    }

    private static final class VertexQueue<E> implements QueueInterface<E> {
        private final ArrayDeque<E> elements = new ArrayDeque<>();

        @Override
        public void enqueue(E element) {
            elements.addLast(element);
        }

        @Override
        public E dequeue() {
            return elements.removeFirst();
        }

        @Override
        public boolean isFull() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return elements.isEmpty();
        }
    }
}
