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
#define read(...) int __VA_ARGS__; re(__VA_ARGS__);
template<class T> using min_priority_queue = priority_queue<T, vector<T>, greater<T>>;
template<class T, class H> istream& operator>>(istream& in, pair<H, T>& a) {in >> a.fi >> a.se; return in;};
template<typename T> istream& operator>>(istream& in, vector<T>& a) {for(auto &x: a) in >> x; return in;};
template<class T> void re(T &x) {cin >> x;}
template<class H, class... T> void re(H &h, T &...t) { re(h); re(t...); }
#pragma endregion

template<typename T> struct fastSegTree {
    vector<T> t;
    int n;
    fastSegTree(vector<T> &arr) {
        n = arr.size();
        t.resize(2*n);
        for (int i = 0; i < n; i++) t[n+i] = arr[i];
        //original array is 0 indexed, seg is 1 indexed
        for (int i = n - 1; i > 0; --i) t[i] = f(t[i<<1], t[i<<1|1]);
    }
    T f(T a, T b) {
        return max(a,b);
    }
    void modify(int p, T value) {  // set value at position p
        for (t[p += n] = value; p > 1; p >>= 1) t[p>>1] = f(t[p], t[p^1]);
    }
    void update(int p, T value) {  // update value at position p
        for (t[p += n] += value; p > 1; p >>= 1) t[p>>1] = f(t[p], t[p^1]);
    }
    T query(int l, int r) {  // sum on interval [l, r)
        T res = 0; //CAREFUL IF MIN/MAX/GCD
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if (l&1) res = f(res, t[l++]);
            if (r&1) res = f(res, t[--r]);
        }
        return res;
    }
};

void solve() {
    read(n,q,c);
    vt<int> stor(n);
    re(stor);
    vt<pii> query(q);
    re(query);
    sort(all(query));

    vt<bool> dontknow(n,0);
    vt<int> zero(n,-1);//pref max, find most recent zero
    if (stor[0] == 0) zero[0] = 0;
    FOR(i,1,n) {
        if (stor[i] == 0) zero[i] = i;
        else zero[i] = zero[i-1];
    }
    F0R(i,n) {
        if (stor[i] == 0) {
            stor[i] = 1;
            dontknow[i] = 1;
        }
    }

    bool bad = 0;
    fastSegTree<int> seg(stor);
    for (auto& [a, h]: query) {
        a--, h--;
        if (seg.query(0,h-1+1) > seg.query(0,a+1)) {
            if (zero[a] == -1) bad = 1;
            else {
                stor[zero[a]] = seg.query(0,h-1+1);
                seg.modify(zero[a], stor[zero[a]]);
                dontknow[zero[a]] = 0;
            }
        }
        if (dontknow[h]) {
            stor[h] = seg.query(0,a+1) +1;
            seg.modify(h, stor[h]);
            dontknow[h] = 0;
        }
    }
    for (int i: stor) if (!(1 <= i && i <= c)) bad = 1;
    for (auto [a,h]: query) {
        if (!(seg.query(0,a+1) < stor[h] && seg.query(0,a+1) >= seg.query(0,h-1+1))) {
            bad = 1;
        }
    }
    if (bad) cout << -1 << '\n';
    else {
        F0R(i,n-1) cout << stor[i] << ' ';
        cout << stor.back() << '\n';
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
    cin >> t;
    while (t--) {
        solve();
    }
}