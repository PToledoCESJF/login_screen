import { Col, Image, message, Row } from "antd";
import { useHistory } from "react-router";
import { useAuth } from "../../context/AuthProvider/useAuth";
import Logo from "../../assets/logo.png";
import { LoginComponent } from "../../components/Login";
import { Fragment } from "react";

export function Login() {
  const auth = useAuth();
  const history = useHistory();

  async function onFinish(values: any) {
    try {
      await auth.authenticate(values.email, values.password);
      history.push('/profile');
    } catch (error) {
      message.error("Email e/ou senha inválidos!");
    }
  }

  const onForgot = () => {
    history.push('/forgot');
  }

  const onSignUp = () => {
    history.push('/profile');
  }

  return (
    <Fragment>
      <Row align='middle' justify='center' style={{ height: '100vh', backgroundColor: '#067bee' }}>
        <Col flex="1 1 200px">
          <Image width='90%' src={Logo} preview={false} />
        </Col>
        <Col flex="0 1 500px" style={{ backgroundColor: '#faf7f5', minWidth: '30%', height: '100vh' }}>
          <Row align='middle' justify='center' style={{ padding: '20px', height: '100%' }}>
            <LoginComponent
              onFinish={(e) => onFinish(e)}
              onForgot={(e) => onForgot()}
              onSignUp={(e) => onSignUp()}
            />
          </Row>
        </Col>
      </Row>
    </Fragment>

  )
}