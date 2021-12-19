export interface iUser {
  email?: string;
  token?: string;
}

export interface iContext extends iUser {
  authenticate: (email: string, password: string) => Promise<void>;
  forgot: (email: string) => Promise<void>;
  logout: () => void;
  create: (name: string, email: string, password: string) => Promise<void>;
}

export interface iAuthProvider {
  children: JSX.Element;
}
