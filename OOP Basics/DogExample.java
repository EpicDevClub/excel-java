// A basic example of inheritance

// There is a dog, and there is a husky, and their relation

class Dog {
  int age;
  String name;
  String owner;
  
  public void run() {}
  public void eat() {}
}

class Husky extends Dog {
  String color;
  
  public void dance() {}
}

class Doberman extends Dog {
  int weight;
  
  public void investigate() {}
}
