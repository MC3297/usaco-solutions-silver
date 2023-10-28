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

//redoing cuz old solution was horrendous
//good implementation practice though!
const int N = 1E5+1;
vector<int> adj[N];
int comp[N], curr = 1;
void dfs(int v) {
    comp[v] = curr;
    for (int i: adj[v]) {
        if (!comp[i]) dfs(i);
    }
}
bool good(vector<array<int,3>>& e, vector<int>& perm, int x) {
    curr = 1;
    F0R(i,sz(perm)) {
        adj[i].clear();
        comp[i] = 0;
    }
    FOR(i,x,sz(e)) {
        adj[e[i][1]].pb(e[i][2]);
        adj[e[i][2]].pb(e[i][1]);
    }

    FOR(i,1,sz(perm)) {
        if (!comp[i]) {
            dfs(i);
            curr++;
        }
    }

    FOR(i,1,sz(perm)) if (comp[i] != comp[perm[i]]) return 0;
    return 1;
}
void solve() {
    re(n,m);
    vector<int> perm(n+1);
    F0R(i,n) cin >> perm[i+1];
    vector<array<int,3>> e(m);
    F0R(i,m) read(e[i][1], e[i][2], e[i][0]);
    
    sort(all(e));
    int l = 0, r = m+1;
    while (l < r) {
        int mid = (l+r+1)/2;
        if (good(e, perm, mid)) l = mid;
        else r = mid-1;
    }
    
    debug(e);
    if (l == m+1) cout << -1 << '\n';
    else cout << e[l][0] << '\n';
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen("wormsort.in","r",stdin);
    freopen("wormsort.out","w",stdout);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}