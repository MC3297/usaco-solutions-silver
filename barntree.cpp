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

const int N = 2E5+1;
vector<int> adj[N], adj2[N];
int hay[N], sum[N], pa[N], each, indeg[N];
vector<array<int,3>> ans;
void dfs_sub(int v, int p) {
    pa[v] = p;
    for (int i: adj[v]) if (i != p) {
        dfs_sub(i,v);
        sum[v] += sum[i];
    }
    sum[v] += hay[v];
}
int haysum_from(int a, int b) {
    if (pa[b] == a) return sum[b];
    return -sum[a];
}
void solve() {
    re(n);
    F0R(i,n) {
        cin >> hay[i+1];
        each += hay[i+1];
    }
    each /= n;
    F0R(i,n) hay[i+1] -= each;
    F0R(i,n-1) {
        re(a,b);
        adj[a].pb(b);
        adj[b].pb(a);
    }

    dfs_sub(1,0);
    //printv(sum,1,n);
    //printv(pa,1,n);
    
    FOR(i,1,n+1) {
        for (int j: adj[i]) {
            if (haysum_from(i,j) < 0) {
                adj2[i].pb(j);
                indeg[j]++;
            }
        }
    }

    //printv(adj2, 1, n);
    //topo sort
    queue<int> q;
    FOR(i,1,n+1) if (!indeg[i]) q.push(i);

    while (sz(q)) {
        int v = q.front();
        q.pop();

        for (int i: adj2[v]) {
            int val = haysum_from(v,i);
            ans.pb({v, i, -val});
            hay[i] += -val;
            
            indeg[i]--;
            if (!indeg[i]) q.push(i);
        }
    }
    debug(ans);
    //printv(hay,1,n);
    cout << sz(ans) << '\n';
    for (auto [a,b,v]: ans) 
        cout << a << ' ' << b << ' ' << v << '\n';
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