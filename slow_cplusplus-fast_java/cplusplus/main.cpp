#include <iostream>
#include <chrono>

using namespace std::chrono;

struct MyDoublyLinkedList {
public:
    struct Node {
        int *data;
        struct Node *prev;
        struct Node *next;

        virtual void dummy() {};

        Node() {}
    };

    Node *head = NULL;
    Node *tail = NULL;

    void add(Node &newNode);

    void del(Node *node);

    void traverse();

    void mixPointers(int size);
};

void MyDoublyLinkedList::add(Node &newNode) {

    // if list is empty, head and tail points to newNode.
    if (head == NULL) {
        head = &newNode;
        tail = &newNode;
        head->prev = NULL;
        tail->next = NULL;
        // else add new node to the end of the list.
    } else {
        tail->next = &newNode;
        newNode.prev = tail;
        tail = &newNode;
        tail->next = NULL;
    }
}

void MyDoublyLinkedList::del(Node *node) {
        node->next->prev = node->prev;
        node->prev->next = node->next;
}

void MyDoublyLinkedList::traverse() {
    int counter = 1;
    struct Node *ptr = head;

    while (ptr != NULL) {
        ptr = ptr->next;
        counter++;
    }

    ptr = tail;
    while(ptr != NULL) {
        ptr = ptr->prev;
    }
}

void MyDoublyLinkedList::mixPointers(int size) {
    std::cout << "Start mixing the pointers... " << std::endl;
    Node *i1 = head;
    Node *temp = head->next;
    Node *i2 = tail;
    int counter = 0;
    while (counter < size / 2) {

        i1->next = i2;
        i2->next = temp;

        temp = temp->next;
        i2 = i2->prev;
        i1 = i1->next->next;
        counter++;
        if (counter == size / 2) {
            i1->next = NULL;
        }
    }

    counter = 0;
    i1= tail;
    temp = tail->prev;
    i2 = head;
    while (counter < size / 2) {
        i1->prev = i2;
        i2->prev = temp;

        i1 = temp;
        temp = temp->prev;
        i2 = i2->next->next;

        counter++;
        if (counter == size / 2) {
            i1->prev = NULL;
        }
    }
    std::cout << "Finish mixing the pointers... " << std::endl;
}

int main() {
    auto start = high_resolution_clock::now();
    int count = 200000000;
    auto *list = new MyDoublyLinkedList();
    auto time_start = high_resolution_clock::now();
    for (int i = 0; i < count; i++) {

        auto *newNode = new MyDoublyLinkedList::Node();
        int variable = i * 3;
        newNode->data = &variable;
        list->add(*newNode);
    }
    auto time_stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(time_stop - time_start);
    std::cout << "Adding duration: " << duration.count() << " milliseconds\n-----------------" << std::endl;

    int counter = count;
    struct MyDoublyLinkedList::Node *itr;
    itr = list->head;

    // delete nodes from list
    time_start = high_resolution_clock::now();

    for (int i = 1; i < count; i++) {
        itr = itr->next;
        if (i % 10 != 0 && i > 2) {
            if (itr->prev->prev != NULL) {
                list->del(itr->prev);
            }
            counter--;
        }
    }

    time_stop = high_resolution_clock::now();
    duration = duration_cast<milliseconds>(time_stop - time_start);
    std::cout << "Deleting duration: " << duration.count() << " milliseconds" << std::endl;

    // size of list
    std::cout << "Size of list: " << counter << std::endl;

    //mix node pointers in list.
    time_start = high_resolution_clock::now();
    list->mixPointers(counter);
    time_stop = high_resolution_clock::now();
    duration = duration_cast<milliseconds>(time_stop - time_start);
    std::cout << "Mixing duration: " << duration.count() << " milliseconds" << std::endl;

    // count the traversal time.
    time_start = high_resolution_clock::now();
    // traverse the list
    std::cout << "Start traversing the list... " << std::endl;
    for (int i = 0; i < 5; i++) {
        list->traverse();
    }
    std::cout << "Finish traversing the list... " << std::endl;

    time_stop = high_resolution_clock::now();
    duration = duration_cast<milliseconds>(time_stop - time_start);
    std::cout << "Traverse duration: " << duration.count() << " milliseconds" << std::endl;

    // count the total time of the program execution.
    time_stop = high_resolution_clock::now();
    duration = duration_cast<milliseconds>(time_stop - start);
    std::cout << "Program duration: " << duration.count() << " milliseconds" << std::endl;
    return 0;
}
