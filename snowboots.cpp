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
    read(n,b);
    vt<int> stor(n);
    re(stor);
    vt<pii> boots(b);
    re(boots);

    vt<vt<bool>> vis(n, vt<bool>(b,0));

    queue<pii> q;
    q.push({0,0});//pos, boot id
    vis[0][0] = 1;
    while (sz(q)) {
        auto [pos, boot] = q.front();
        auto [depth, far] = boots[boot];
        q.pop();

        FOR(i,pos+1,min(pos+far+1,n)) {//keep the boot
            if (stor[i] <= depth && !vis[i][boot]) {
                vis[i][boot] = 1;
                q.push({i, boot});
            }
        }

        FOR(i,boot+1,b) {//switch the boot
            if (boots[i].fi >= stor[pos] && !vis[pos][i]) {
                vis[pos][i] = 1;
                q.push({pos, i});
            }
        }
        debug(q);
    }

    debug(vis);
    F0R(i,b) {
        if (vis[n-1][i]) {
            cout << i << '\n';
            return;
        }
    }
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen("snowboots.in","r",stdin);
    freopen("snowboots.out","w",stdout);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}