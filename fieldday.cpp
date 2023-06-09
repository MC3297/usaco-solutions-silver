#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using pii = pair<int,int>;
#define fi first
#define se second
#define pb push_back 
#define lb lower_bound
#define ub upper_bound
#define all(v) v.begin(), v.end()
#define YES cout << "YES" << '\n'
#define NO cout << "NO" << '\n'
#define IMP cout << "IMPOSSIBLE" << '\n'
#define FOR(i,s,e) for (int i = (s); i < (e); ++i) 
#define F0R(i,e) FOR(i,0,e) 
#define ROF(i,s,e) for (int i = (b)-1; i >= (a); --i) 
#define R0F(i,e) ROF(i,0,e) 
#define printv(v,s,e) FOR(i,s,e+1) cout << v[i] << ' '; cout << '\n'
template<class T> using min_priority_queue = priority_queue<T, vector<T>, greater<T>>;
template<typename T> istream& operator>>(istream& in, vector<T>& a) {for(auto &x: a) in >> x; return in;};

void solve() {
    int c,n; cin >> c >> n;
    vector<int> stor(n, 0), dist(1<<c, -1);
    queue<int> q;
    F0R(i,n) {
        string s; cin >> s;
        for (char ch: s) {
            stor[i]<<=1;
            stor[i] += ch=='H';
        }
        dist[stor[i]] = 0;
        q.push(stor[i]);
    }
    while (q.size()) {
        int v = q.front();
        q.pop();
        F0R(j,c) {
            int i = v^(1<<j);
            if (dist[i] == -1) {
                dist[i] = dist[v]+1;
                q.push(i);
            }
        }
    }
    for (int a: stor) cout << c-dist[((1<<c) - 1)^a] << '\n';
}
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}