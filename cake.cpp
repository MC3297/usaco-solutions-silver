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
#define printv(v,s,e) FOR(z,s,e+1) cout << v[z] << ' '; cout << '\n'
#define ir(...) int __VA_ARGS__; re(__VA_ARGS__);
#define cmp(x,y) [&](auto&aa,auto&bb){return x aa y<x bb y;}
template<class T> using min_priority_queue = priority_queue<T, vector<T>, greater<T>>;
template<class T, class H> istream& operator>>(istream& in, pair<H, T>& a) {in >> a.fi >> a.se; return in;};
template<typename T> istream& operator>>(istream& in, vector<T>& a) {for(auto &x: a) in >> x; return in;};
template<class T> void re(T &x) {cin >> x;}
template<class H, class... T> void re(H &h, T &...t) { re(h); re(t...); }
template<class T> void pr(T &x) {cout << (x) << '\n';}
template<class H, class... T> void pr(H &h, T &...t) { cout << (h) << ' '; pr(t...); }
#pragma endregion

void solve() {
    ir(n);
    vt<int> a(n); re(a);
    
    int l = n/2-1, r = n-1;
    int cur = 0;
    F0R(i,n/2-1) cur += a[i];
    int elsie = cur;
    F0R(i,n/2-1) {
        cur -= a[l-1];
        l--;
        cur += a[r];
        r--;
        elsie = max(elsie, cur);
    }
    int bessie = accumulate(all(a), 0ll) - elsie;
    pr(bessie, elsie);
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    ios_base::sync_with_stdio(0); cin.tie(0);
    #endif
    int t = 1;
    cin >> t;
    while (t--) {
        solve();
    }
}