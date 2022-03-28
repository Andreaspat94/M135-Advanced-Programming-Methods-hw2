#include <iostream>
#include <chrono>
using namespace std::chrono;

struct MyDoublyLinkedList {
public:
    struct Node {
        int *data;
        struct Node *prev;
        struct Node *next;

        Node() {}
    };

    Node* head = NULL;
    Node* tail = NULL;

    void add(Node &newNode);
    virtual void dummy() {};
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

int main() {
    std::cout << "Start filling the list..." << std::endl;
    auto start = high_resolution_clock::now();
    int count = 250000000;
    auto* list = new MyDoublyLinkedList();
    for(int i = 0; i < count; i ++) {
        auto* newNode = new MyDoublyLinkedList::Node();
        int variable = i * 3;
        newNode->data = &variable;

        if (10550 % (i+1) > 500) {
            variable = i * 4;
            newNode->data = &variable;
        }
        list->add(*newNode);
    }
    auto stop_add = high_resolution_clock::now();
	
    auto duration = duration_cast<milliseconds>(stop_add - start);
    
    auto stop = high_resolution_clock::now();
    duration = duration_cast<milliseconds>(stop - start);
    std:: cout << "Program duration: " << duration.count() << " milliseconds" << std::endl;
    return 0;
}