#include <bits/stdc++.h>
using namespace std;
using ll = long long;
//#define int ll
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
#define ROF(i,s,e) for (int i = (b)-1; i >= (a); --i) 
#define R0F(i,e) ROF(i,0,e) 
#define printv(v,s,e) FOR(i,s,e+1) cout << v[i] << ' '; cout << '\n'
template<class T> using min_priority_queue = priority_queue<T, vector<T>, greater<T>>;
template<class T, class H> istream& operator>>(istream& in, pair<H, T>& a) {in >> a.fi >> a.se; return in;};
template<typename T> istream& operator>>(istream& in, vector<T>& a) {for(auto &x: a) in >> x; return in;};
template<class T> void read(T &x) {cin >> x;}
template<class H, class... T> void read(H &h, T &...t) { read(h); read(t...); }

char flip(char a) {
    if (a == 'R') return 'L';
    else return 'R';
}
void flipcol(int col, vector<string> &arr) {
    F0R(i,sz(arr)) arr[i][col] = flip(arr[i][col]);
}
void fliprow(int row, vector<string> &arr) {
    F0R(i,sz(arr)) arr[row][i] = flip(arr[row][i]);
}
int sum(vector<string> &a) {
    int ans = 0;
    F0R(i,sz(a)) F0R(j,sz(a)) ans += a[i][j] == 'R';
    return ans;
}
void solve() {
    int n; cin >> n;
    vector<string> a(n), b;
    read(a);
    b = a;
    F0R(i,n) {
        if (a[0][i] != 'L') {
            flipcol(i,a);
        }
    }
    FOR(i,1,n) {
        if (a[i][0] != 'L') {
            fliprow(i,a);
        }
    }
    
    F0R(i,n) {
        if (b[n-1][i] != 'L') {
            flipcol(i,b);
        }
    }
    F0R(i,n-1) {
        if (b[i][n-1] != 'L') {
            fliprow(i,b);
        }
    }

    if (sum(a) == 1) {
        F0R(i,n) F0R(j,n) if (a[i][j] == 'R') cout << i+1 << ' ' << j+1 << '\n';
    }
    else if (sum(b) == 1) {
        F0R(i,n) F0R(j,n) if (b[i][j] == 'R') cout << i+1 << ' ' << j+1 << '\n';
    }
    else cout << -1 << '\n';
}
signed main() {
    freopen("leftout.in","r",stdin);
    freopen("leftout.out","w",stdout);
    ios_base::sync_with_stdio(0); cin.tie(0);
    int t = 1;
    //cin >> t;
    while (t--) {
        solve();
    }
}