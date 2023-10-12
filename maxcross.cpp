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

bool good(vector<int>& stor, int k, int ans) {
    bool good = 0;
    FOR(i,1,sz(stor)-ans) {
        good |= (stor[i+ans]-stor[i-1]-1) >= k;
    }
    return good;
}
void solve() {
    re(n,k,b);
    vector<int> stor(b); cin >> stor;
    stor.pb(0);
    stor.pb(n+1);
    sort(all(stor));


    int l = 0, r = b;
    while (l < r) {
        int mid = (l+r)/2;
        if (good(stor,k,mid)) r = mid;
        else l = mid+1;
    }
    cout << l << '\n';
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    freopen("maxcross.in","r",stdin);
    freopen("maxcross.out","w",stdout);
    ios_base::sync_with_stdio(0); cin.tie(0);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}