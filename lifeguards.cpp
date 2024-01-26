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
    vt<pii> stor(n);
    re(stor);

    vt<pii> pts;//individual pts
    F0R(i,n) {
        pts.pb({stor[i].fi,1});
        pts.pb({stor[i].se,-1});
    }
    sort(all(pts));
    debug(pts);

    vt<int> freq(2*n);//# of intervals covering (pts[i-1],pts[i])
    freq[0] = 0;
    FOR(i,1,2*n) {
        freq[i] = freq[i-1] + pts[i-1].se;
    }
    debug(freq);

    vt<int> pref(2*n);//pref sum of ranges covered by 1 interval
    pref[0] = 0;
    FOR(i,1,2*n) {
        pref[i] = pref[i-1];
        if (freq[i] == 1) {
            pref[i] += pts[i].fi - pts[i-1].fi;
        }
    }
    debug(pref);
    
    int total = 0;
    FOR(i,1,2*n) {
        if (freq[i]) total += pts[i].fi - pts[i-1].fi;
    }
    debug(total);

    int ans = 0;
    for (auto [l,r]: stor) {
        int a = lb(all(pts),make_pair(l,1ll)) - pts.begin();
        int b = lb(all(pts),make_pair(r,-1ll)) - pts.begin();
        int removed = pref[b]-pref[a];
        ans = max(ans, total-removed);
    }
    cout << ans << '\n';
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen("lifeguards.in","r",stdin);
    freopen("lifeguards.out","w",stdout);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}