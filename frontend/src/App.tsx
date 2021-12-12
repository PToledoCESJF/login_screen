import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom';
import { Login } from './components/Login';
import { ProtectedLayout } from './components/ProtectedLayout';
import { AuthProvider } from './context/AuthProvider';

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
          <Redirect to='/login' />
        </Switch>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
