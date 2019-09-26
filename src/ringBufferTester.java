public class ringBufferTester {
    public static void main(String[] args) {
        RingBuffer<Integer> integerRingBuffer = new RingBuffer<>(10);

        for (int i = 0; i < 10; i++) {
            integerRingBuffer.enqueue(i);
        }

        System.out.println(integerRingBuffer.peek());
        System.out.println(integerRingBuffer.isEmpty());
        System.out.println(integerRingBuffer.dequeue());
        integerRingBuffer.enqueue(10);
        try{
            integerRingBuffer.enqueue(11);
        }
        catch (Exception e){
            System.out.println("queue is full");
        }

        System.out.println(integerRingBuffer);
    }
}
