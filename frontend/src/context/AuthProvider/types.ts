export interface iUser {
  email?: string;
  token?: string;
}

export interface iContext extends iUser {
  authenticate: (email: string, password: string) => Promise<void>;
  logout: () => void;
}

export interface iAuthProvider {
  children: JSX.Element;
}
