import { Button, Checkbox, Col, Form, Image, Input, message, Row } from "antd";
import { MailOutlined, LockOutlined } from '@ant-design/icons';
import { Fragment } from "react";
import { useHistory } from "react-router";
import { useAuth } from "../../context/AuthProvider/useAuth";
import Logo from "../../assets/logo.png";

export const Login = () => {
  const auth = useAuth();
  const history = useHistory();

  async function onFinish(values: { email: string, password: string }) {
    try {
      await auth.authenticate(values.email, values.password);
      history.push('/profile');
    } catch (error) {
      message.error("Email e/ou senha inválidos!");
    }
  }

  return (
    <Fragment>
      <Row align='middle' justify='center' style={{ height: '100vh', backgroundColor: '#067bee' }}>
        <Col flex="1 1 200px">
          <Image width='90%' src={Logo} preview={false} />
        </Col>
        <Col flex="0 1 500px" style={{ backgroundColor: '#faf7f5', minWidth: '30%', height: '100vh' }}>
          <Row align='middle' justify='center' style={{ padding: '20px', height: '100%' }}>
            <Form name="normal_login" initialValues={{ remember: true }} onFinish={onFinish} style={{ width: '100%', }}>
              <Form.Item name="email" rules={[{ required: true, message: 'Entre com seu email.' }]}>
                <Input prefix={<MailOutlined className="site-form-item-icon" />} placeholder="Email..." />
              </Form.Item>
              <Form.Item name="password" rules={[{ required: true, message: 'Entre com sua senha.' }]}>
                <Input prefix={<LockOutlined className="site-form-item-icon" />} type="password" placeholder="Senha..." />
              </Form.Item>
              <Form.Item name="remember" valuePropName="checked" noStyle>
                <Checkbox>Lembre de mim</Checkbox>
              </Form.Item>
              <Form.Item style={{ paddingTop: '10px' }}>
                <Button type="link" style={{ float: 'left' }}> Esqueci a senha </Button>
                <Button type="link" style={{ float: 'right' }}> Registre-me agora </Button>
              </Form.Item>
              <Form.Item>
                <Row>
                  <Button type="primary" htmlType="submit" style={{ width: '100%' }}> Entrar </Button>
                </Row>
              </Form.Item>
            </Form>
          </Row>
        </Col>
      </Row>
    </Fragment>
  )
}