package Week11;

import week5.QueueInterface;

public final class GraphTraversals {
    private GraphTraversals() {
    }

    public static <T> boolean hasDirectFlight(
            WeightedGraphInterface<T> graph, T from, T to) {
        // TODO: Use one call to weightIs.
        throw new UnsupportedOperationException("Not implemented");
    }

    public static <T> int countDirectDestinations(
            WeightedGraphInterface<T> graph, T city) {
        QueueInterface<T> destinations = graph.getToVertices(city);
        int count = 0;
        // TODO: Dequeue and count destinations until the queue is empty.
        throw new UnsupportedOperationException("Not implemented");
    }
}
