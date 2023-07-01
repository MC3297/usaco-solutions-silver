#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using pii = pair<int,int>;
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
template<class T> using min_priority_queue = priority_queue<T, vector<T>, greater<T>>;
template<class T, class H> istream& operator>>(istream& in, pair<H, T>& a) {in >> a.fi >> a.se; return in;};
template<typename T> istream& operator>>(istream& in, vector<T>& a) {for(auto &x: a) in >> x; return in;};
template<class T> void read(T &x) {cin >> x;}
template<class H, class... T> void read(H &h, T &...t) { read(h); read(t...); }

void solve() {
    int n,ex,ey; read(n,ex,ey);
    vector<pii> stor(n); cin >> stor;

    vector<pii> a[n/2+1], b[n-n/2+1];
    F0R(i,1<<(n/2)) {//first half
        int x = 0, y = 0;
        F0R(j,n/2) {
            if (i&(1<<j)) {
                x += stor[j].fi;
                y += stor[j].se;
            }
        }
        a[__builtin_popcount(i)].pb({x,y});
    }

    F0R(i,1<<(n-n/2)) {//sec half
        int x = 0, y = 0;
        F0R(j,n-n/2) {
            if (i&(1<<j)) {
                x += stor[j+n/2].fi;
                y += stor[j+n/2].se;
            }
        }
        b[__builtin_popcount(i)].pb({x,y});
    }

    F0R(i,n/2) sort(all(a[i]));
    F0R(i,n-n/2) sort(all(b[i]));

    vector<ll> ans(n+1,0);
    F0R(i,n/2+1) {
        F0R(j,n-n/2+1) {
            for (auto [f,s]: a[i]) {
                pii p = {ex-f,ey-s};
                ans[i+j] += ub(all(b[j]),p)-lb(all(b[j]),p);
            }
        }
    }
    FOR(i,1,n+1) cout << ans[i] << '\n';
}
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0);
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}