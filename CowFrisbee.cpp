#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
main() {
    int n;
    cin >> n;
    vector<int> stor(n);
    for (int i = 0; i < n; i++) cin >> stor[i];
    //monotonic stack decreasing
    vector<pair<int,int>> stack;
    ll ans = 0;
    for (int i = 0; i < n; i++) {
        if (!stack.empty()) ans += i - stack.back().second+1;
        while (!stack.empty() && stack.back().first < stor[i]) {
            stack.pop_back();
            if (!stack.empty()) ans += i - stack.back().second+1;
        }
        stack.push_back({stor[i], i});
    }
    cout << ans << '\n';
}