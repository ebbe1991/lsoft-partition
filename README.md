# Partition
The Partition-Project is a Java-Project to easy partitioning objects.
First of all you have objects to be partitioned, for example a list:

      List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");

Now you need some partitions :-):

        new Partition(1, 2) // Partition 1 of 2 total parts
        new Partition(1, 2) // Partition 2 of 2 total parts

The CollectionPartitioning is simple to use:

        List<String> animals = new ArrayList<>();
        animals.add("Mouse");
        animals.add("Cat");
        animals.add("Dog");
        animals.add("Fish");

        Map<Integer, List<String>> result = CollectionPartitioning
                .forObjects(animals)
                .withPartitions(new Partition(1, 2))
                .withPartitions(new Partition(2, 2))
                .invoke();
Result: <p>Part 1: Mouse, Cat <br>
          Part 2: Dog, Fish
          
For more information look at the javadoc or tests.
