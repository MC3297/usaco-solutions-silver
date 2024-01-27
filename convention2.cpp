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


void solve() {
    read(n);
    vt<array<int,3>> stor(n);
    F0R(i,n) {
        read(a,t);
        stor[i] = {a,t,i};
        //arrival, time to eat, seniority
    }
    sort(all(stor));
    debug(stor);

    int ans = 0, fin = stor[0][0], ind = 1;
    min_priority_queue<array<int,3>> s;//seniority, time to eat, arrival
    s.push({stor[0][2],stor[0][1],stor[0][0]});
    while (sz(s)) {
        auto [senior, eat, arrive] = s.top();
        s.pop();
        ans = max(ans, fin-arrive);

        fin += eat;
        while (ind < n && stor[ind][0] <= fin) {
            s.push({stor[ind][2],stor[ind][1],stor[ind][0]});
            ind++;
        }
        if (ind < n && sz(s) == 0) {
            s.push({stor[ind][2],stor[ind][1],stor[ind][0]});
            fin = stor[ind][0];
            ind++;
        }
    }
    cout << ans << '\n';
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen("convention2.in","r",stdin);
    freopen("convention2.out","w",stdout);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}