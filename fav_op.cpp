#pragma region
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
#define int ll
using pii = pair<int,int>;
#ifndef LOCAL
#pragma GCC optimize("O3,unroll-loops")
#pragma GCC target("avx2,bmi,bmi2,lzcnt,popcnt")
#define debug(...)
#endif
#define vt vector
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
#define cmp(x,y) [&](auto&hawk,auto&tuah){return x hawk y<x tuah y;}
template<class T> using min_priority_queue = priority_queue<T, vector<T>, greater<T>>;
template<class T, class H> istream& operator>>(istream& in, pair<H, T>& a) {in >> a.fi >> a.se; return in;};
template<typename T> istream& operator>>(istream& in, vector<T>& a) {for(auto &x: a) in >> x; return in;};
template<class T> void re(T &x) {cin >> x;}
template<class H, class... T> void re(H &h, T &...t) { re(h); re(t...); }
template<class T> void pr(T &x) {cout << (x) << '\n';}
template<class H, class... T> void pr(H &h, T &...t) { cout << (h) << ' '; pr(t...); }
#pragma endregion

/*
ai-x = 0 (mod M)
ai = x (mod M)

idea is that a_i repeats every mod m basically
choose which a_i to add m to such that
the array clumps together as close as possible
then just choose median and find ans
*/
void solve() {
    ir(n,m);
    vt<int> a(n); re(a);
    for (int& i: a) i %= m;
    sort(all(a));
    debug(a);

    //maintain lesser and greater halves
    queue<int> l,r;
    int lsum = 0, rsum = 0;
    F0R(i,n/2) l.push(a[i]), lsum += a[i];
    FOR(i,n/2,n) r.push(a[i]), rsum += a[i];

    int med = r.front();//sz(r) >= s(l)
    int ans = rsum - med*sz(r) + med*sz(l) - lsum;
    F0R(i,n) {
        //make the smallest from l into largest in r by adding m
        rsum += l.front()+m, r.push(l.front()+m);
        lsum += r.front(),   l.push(r.front());
        lsum -= l.front(),   l.pop();
        rsum -= r.front(),   r.pop();
        med = r.front();
        ans = min(ans, rsum - med*sz(r) + med*sz(l) - lsum);
    }
    pr(ans);
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