import { createContext, useEffect, useState } from "react";
import { iAuthProvider, iContext, iUser } from "./types";
import { getUserLocalStorage, LoginRequest, setUserLocalStorage } from "./utils";

export const AuthContext = createContext<iContext>({} as iContext)

export const AuthProvider = ({ children }: iAuthProvider) => {
  const [user, setUser] = useState<iUser | null>();

  useEffect(() => {
    const user = getUserLocalStorage();
    if (user) {
      setUser(user);
    }
  }, []);

  async function authenticate(email: string, password: string) {
    const response = await LoginRequest(email, password);
    const payload = { token: response.token, email };
    setUser(payload);
    setUserLocalStorage(payload);
  }

  function logout() {
    setUser(null);
    setUserLocalStorage(null);
  }

  return (
    <AuthContext.Provider value={{ ...user, authenticate, logout }}>
      {children}
    </AuthContext.Provider>
  )
}
