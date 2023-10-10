#include <bits/stdc++.h>
using namespace std;

bool good(vector<int>& stor, int m, int c, int ans) {
    int currbus = stor[0], numcows = 0;
    m--;
    for (int i = 0; i < stor.size(); i++) {
        if (stor[i]-currbus <= ans) {
            if (numcows < c) {
                numcows++;
            }
            else {
                currbus = stor[i];
                numcows = 1;
                m--;
            }
        }
        else {
            currbus = stor[i];
            numcows = 1;
            m--;
        }
        
    }
    if (m >= 0) return 1;
    else return 0;
}
int main() {
    freopen("convention.in", "r", stdin);
    freopen("convention.out", "w", stdout);

    int n,m,c; cin >> n >> m >> c;
    vector<int> stor(n);
    for (int i = 0; i < n; i++) {
        cin >> stor[i];
    }

    sort(stor.begin(), stor.end());


    int l = 0, r = 1E9;
    while (l < r) {
        int mid = (l+r)/2;
        if (good(stor,m,c,mid)) r = mid;
        else l = mid+1;
    }
    cout << l << '\n';
}
