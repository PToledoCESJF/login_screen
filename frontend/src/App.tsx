import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import { Login } from './page/Login';
import { ProtectedLayout } from './components/ProtectedLayout';
import { AuthProvider } from './context/AuthProvider';
import { Forgot } from './page/Forgot';

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Switch>
          <Route path='/profile'>
            <ProtectedLayout>
              <h1>Este é o componente Profile.</h1>
            </ProtectedLayout>
          </Route>
          <Route path='/login'>
            <Login />
          </Route>
          <Route path='/forgot'>
            <Forgot />
          </Route>
          <Redirect to='/login' />
        </Switch>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
