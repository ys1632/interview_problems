#include <iostream>

using namespace std;

struct TreeNode {
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x), left(NULL), right(NULL){}
};

int sum(TreeNode* root) {
	if (!root) {
		return 0;
	}
	else if (!root->left && !root->right) {
		return root->val;
	}
	else if (!root->left) {
		return sum(root->right) + root->val;
	}
	else if (!root->right) {
		return sum(root->left) + root->val;
	}
	else {
		return min(sum(root->left), sum(root->right)) + root->val;
	}
	
}

int main() {
	TreeNode * a = new TreeNode(2);
	a->left = new TreeNode(5);
	a->right = new TreeNode(6);
	a->left->left = new TreeNode(18);
	a->left->right = new TreeNode(4);
	a->left->right->left = new TreeNode(2);
	a->right->right = new TreeNode(100);
	cout << sum(a) << endl;

	return 0;
}