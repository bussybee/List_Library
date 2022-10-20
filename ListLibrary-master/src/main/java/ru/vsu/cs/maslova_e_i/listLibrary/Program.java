package ru.vsu.cs.maslova_e_i.listLibrary;

public class Program {
    public static void main(String[] args) {
        LinkedList<String> linkList = new LinkedList<String>();

        //добавление элементов в список
        linkList.add("Germany");
        linkList.add("France");
        linkList.add("Great Britain");
        linkList.add("Spain");
        //добавление элемента в список по индексу
        linkList.add(1, "Italy");
        System.out.printf("List has %d elements \n", linkList.size());

        //получение элемента из списка по индексу
        System.out.println(linkList.get(1));

        //проверка содержится ли элемент в списке
        if(linkList.contains("Germany")){

            System.out.println("List contains Germany");
        }

        //удаление элемента из списка по индексу
        linkList.remove(1);
        // удаление первого элемента
        linkList.removeFirst();
        // удаление последнего элемента
        linkList.removeLast();
        System.out.printf("List has %d elements \n", linkList.size());

        CycleLinkedList cll = new CycleLinkedList();

        //добавление элементов в циклический список
        cll.addNode(13);
        cll.addNode(7);
        cll.addNode(24);
        cll.addNode(1);
        cll.addNode(8);
        cll.addNode(37);
        cll.addNode(46);

        //проверка на содержание элемента в циклическом списке
        if(cll.containsNode(13)){
            System.out.println("CycleList contains number 13");
        }

        //удаление элемента из циклического списка
        cll.deleteNode(46);
        if(cll.containsNode(46)){
            System.out.println("CycleList contains number 46");
        } else{
            System.out.println("CycleList don't contains number 46");
        }
    }
}
