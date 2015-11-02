#include <iostream>

using namespace std;

struct CNode {
	int value;
	CNode *next;
	//CNode(int x):value(x), next(NULL) {}
};

CNode *insertCycleList(CNode *start, int n) {
	CNode * q = new CNode();
	q->value = n;

	cout << q->value << endl;
	//no node in the cycle
	if (!start) {
		q->next = q;
		return q;
	}

	//one node in the cycle
	if (!start->next) {
		start->next = q;
		q->next = start;
		return q;
	}


	CNode *maxNode = start;
	CNode *cur = start;
	CNode *pre = new CNode();

	do {
		pre = cur;
		if (cur->value > maxNode->value) {
			maxNode = cur;
		}
		cur = cur->next;
	} while (cur != start);

	//n > max || n < min
	if (n >= maxNode->value || n <= maxNode->next->value) {
		q->next = maxNode->next;
		maxNode->next = q;
	} //min < n < max
	else {
		while(true) {
			if (n > pre->value && n < cur->value) {
				break;
			}
			pre = cur;
			cur = cur->next;
		}
		pre->next = q;
		q->next = cur;
	}

	//print results
	CNode * test = q;
	do {
		cout << test->value << " ";
		test = test->next;
	} while(test != q);

	return q;
	
}

int main() {
	//test case: 1,3,5,7
	CNode *head = new CNode();
	head->value = 1;
	head->next = new CNode();
	head->next->value = 3;
	head->next->next = new CNode();
	head->next->next->value = 5;
	CNode *last = new CNode();
	last->value = 7;
	head->next->next->next = last;
	last->next = head;

	CNode *root = insertCycleList(head->next, 4);

	
	return 0;
}