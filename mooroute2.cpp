#pragma region
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
#define int ll
using pii = pair<int,int>;
#ifndef LOCAL
#define debug(...)
#endif
#define fi first
#define se second
#define pb push_back 
#define lb lower_bound
#define ub upper_bound
#define sz(x) (int)(x).size()
#define all(v) v.begin(), v.end()
#define rall(v) v.rbegin(), v.rend()
#define YES cout << "YES" << '\n'
#define NO cout << "NO" << '\n'
#define IMP cout << "IMPOSSIBLE" << '\n'
#define FOR(i,s,e) for (int i = (s); i < (e); ++i) 
#define F0R(i,e) FOR(i,0,e) 
#define ROF(i,s,e) for (int i = (e)-1; i >= (s); --i) 
#define R0F(i,e) ROF(i,0,e) 
#define printv(v,s,e) FOR(i,s,e+1) cout << v[i] << ' '; cout << '\n'
#define re(...) int __VA_ARGS__; read(__VA_ARGS__);
template<class T> using min_priority_queue = priority_queue<T, vector<T>, greater<T>>;
template<class T, class H> istream& operator>>(istream& in, pair<H, T>& a) {in >> a.fi >> a.se; return in;};
template<typename T> istream& operator>>(istream& in, vector<T>& a) {for(auto &x: a) in >> x; return in;};
template<class T> void read(T &x) {cin >> x;}
template<class H, class... T> void read(H &h, T &...t) { read(h); read(t...); }
#pragma endregion

const int N = 2E5+1;
vector<array<int,3>> adj[N];
int lay[N], dist[N];
void solve() {
    re(n,m);
    F0R(i,m) {
        re(a,r,b,s);
        adj[a].pb({r,s,b});
    }
    F0R(i,n) cin >> lay[i+1];
    lay[1] = 0;
    fill(dist, dist+N, 1E18);
    FOR(i,1,n+1) {
        for (auto& j: adj[i]) {
            j[0] -= lay[i];
        }
        sort(rall(adj[i]));
    }
    vector<int> ind(n+1,0);

    queue<pii> q;
    q.push({1,0});
    while (sz(q)) {
        auto [v,t] = q.front();
        q.pop();
        if (t > dist[v]) continue;
        dist[v] = t;
        
        while (ind[v] < sz(adj[v]) && adj[v][ind[v]][0] >= t) {
            q.push({adj[v][ind[v]][2], adj[v][ind[v]][1]});
            ind[v]++;
        }
        debug(q);
    }
    FOR(i,1,n+1) {
        if (dist[i] == 1E18) cout << -1;
        else cout << dist[i];
        cout << '\n';
    }
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    ios_base::sync_with_stdio(0); cin.tie(0);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}