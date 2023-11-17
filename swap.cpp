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
//swapity swap but bexpo instead of graphs
vector<int> change(vector<int>& og, vector<int>& apply) {
    vector<int> ans(sz(og));
    F0R(i,sz(og)) ans[i] = og[apply[i]-1];
    return ans;
}
void solve() {
    re(n,m,k);
    vector<pii> ops(m);
    cin >> ops;

    vector<int> ans(n), apply;
    iota(all(ans), 1);
    apply = ans;
    for (auto [a,b]: ops) reverse(apply.begin()+a-1, apply.begin()+b);

    while (k) {
        if (k&1) ans = change(ans, apply);
        k >>= 1;
        apply = change(apply, apply);
        debug(ans);
        debug(apply);
    }

    F0R(i,n) cout << ans[i] << '\n';
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen("swap.in","r",stdin);
    freopen("swap.out","w",stdout);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}