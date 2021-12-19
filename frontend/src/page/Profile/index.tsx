import { Col, message, Row } from "antd";
import { Fragment } from "react";
import { useHistory } from "react-router";
import { ProfileComponent } from "../../components/Profile";
import { useAuth } from "../../context/AuthProvider/useAuth";

export const Profile = () => {

  const auth = useAuth();
  const history = useHistory();
  
  async function onFinish(values: { name: string, email: string, password: string }) {
    try {
      await auth.create(values.name, values.email, values.password);
      history.push('/login');
    } catch (error) {
      message.error(`Erro: ${error}`);
    }
  }
  
  return (
    <Fragment>
      <Row align='middle' justify='center' style={{ height: '100vh', backgroundColor: '#067bee' }}>
        <Col flex="0 1 500px" style={{ backgroundColor: '#faf7f5', minWidth: '30%' }}>
   
          <Row align='middle' justify='center' style={{ padding: '20px', height: '100%' }}>
            <h1 style={{ color: '#067bee' }}>Registre-se</h1>
            <ProfileComponent onFinish={(e) => onFinish(e)} />
          </Row>
        </Col>
      </Row>
    </Fragment>
  )
}