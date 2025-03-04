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

int dx[] = {-1,1,0,0}, dy[] = {0,0,1,-1};
char dir[] = {'D', 'U', 'L', 'R'};
void solve() {
    ir(n,m);
    vt<vt<char>> grid(n, vt<char>(n, '?'));
    vt<pii> upd(m);
    F0R(i,m) {
        ir(l,r);
        char c; re(c);
        l--, r--;
        upd[i] = {l,r};
        grid[l][r] = c;
    }
    
    vt<vt<bool>> vis(n, vt<bool>(n));
    auto check = [&] (int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    };
    
    queue<pii> q;
    F0R(i,n) {
        q.push({-1,i});
        q.push({i,-1});
        q.push({n,i});
        q.push({i,n});
    }
    while (sz(q)) {
        auto [r,c] = q.front();
        q.pop();
        
        F0R(k,4) {
            int nr = r+dx[k], nc = c+dy[k];
            if (check(nr, nc) && !vis[nr][nc] && (grid[nr][nc] == dir[k] || grid[nr][nc] == '?')) {
                vis[nr][nc] = 1;
                q.push({nr,nc});
            }
        }
    }
    
    int unusable = 0;
    F0R(i,n) F0R(j,n) unusable += !vis[i][j];
    
    vt<int> ans(m);
    R0F(i,m) {
        ans[i] = unusable;
        
        auto [x,y] = upd[i];
        grid[x][y] = '?';
        
        bool good = 0;
        F0R(k,4) {
            int nx = x+dx[k], ny = y+dy[k];
            if (!check(nx, ny) || vis[nx][ny]) good = 1;
        }
        
        if (good) {
            q.push({x,y});
            if (!vis[x][y]) unusable--;
            vis[x][y] = 1;
        }
        while (sz(q)) {
            auto [r,c] = q.front();
            q.pop();
            
            F0R(k,4) {
                int nr = r+dx[k], nc = c+dy[k];
                if (check(nr, nc) && !vis[nr][nc] && (grid[nr][nc] == dir[k] || grid[nr][nc] == '?')) {
                    vis[nr][nc] = 1;
                    unusable--;
                    q.push({nr,nc});
                }
            }
        }
    }
    for (int i: ans) pr(i);
}
signed main() {
    #ifdef LOCAL
    freopen("txt.in","r",stdin);
    freopen("txt.out","w",stdout);
    #else
    ios_base::sync_with_stdio(0); cin.tie(0);
    #endif
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}