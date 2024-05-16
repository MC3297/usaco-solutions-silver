#pragma region
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
#define int ll
using pii = pair<int,int>;
#define vt vector
#ifndef LOCAL
#pragma GCC optimize("O3,unroll-loops")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")
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
#define ir(...) int __VA_ARGS__; re(__VA_ARGS__);
#define pr(...) cout << (__VA_ARGS__) << '\n'
template<class T> using min_priority_queue = priority_queue<T, vector<T>, greater<T>>;
template<class T, class H> istream& operator>>(istream& in, pair<H, T>& a) {in >> a.fi >> a.se; return in;};
template<typename T> istream& operator>>(istream& in, vector<T>& a) {for(auto &x: a) in >> x; return in;};
template<class T> void re(T &x) {cin >> x;}
template<class H, class... T> void re(H &h, T &...t) { re(h); re(t...); }
#pragma endregion


void solve() {
    ir(n,m,k);
    vt<int> nxt(n+1);
    iota(all(nxt), 0);//sets nxt[i] = i
    //simulate the first M reversals
    F0R(i,m) {
        ir(a,b);
        reverse(nxt.begin()+a, nxt.begin()+b+1);
    }

    //implicit graph where i -> nxt[i]
    vt<int> ans(n+1);
    vt<bool> vis(n+1);
    FOR(i,1,n+1) {
        if (!vis[i]) {
            vis[i] = 1;
            vt<int> cycle = {i};
            int cur = nxt[i];
            while (cur != i) {
                cycle.pb(cur);
                vis[cur] = 1;
                cur = nxt[cur];
            }
            
            int offset = k%sz(cycle);
            for (int i: cycle) {
                ans[i] = cycle[offset%sz(cycle)];
                offset++;
            }
        }
    }
    FOR(i,1,n+1) pr(ans[i]);
}
signed main() {
    #ifdef LOCAL
    #else
    freopen("swap.in","r",stdin);
    freopen("swap.out","w",stdout);
    ios_base::sync_with_stdio(0); cin.tie(0);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}