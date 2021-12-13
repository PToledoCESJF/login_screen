import { Button, Col, Form, Input, Row, message } from "antd";
import { MailOutlined } from '@ant-design/icons';
import { Fragment } from "react";
import { useHistory } from "react-router";
import { useAuth } from "../../context/AuthProvider/useAuth";

export const Forgot = () => {
  const auth = useAuth();
  const history = useHistory();
  
  async function onFinish(values: { email: string }) {
    try {
      await auth.forgot(values.email);
      history.push('/auth/forgot');
    } catch (error) {
      message.error("Email não encontrado em nossa base de dados");
    }
  }

  return (
    <Fragment>
      <Row align='middle' justify='center' style={{ height: '100vh', backgroundColor: '#067bee' }}>
        <Col flex="0 1 500px" style={{ backgroundColor: '#faf7f5', minWidth: '30%' }}>
          <Row align='middle' justify='center' style={{ padding: '20px', height: '100%' }}>
            <Form name="normal_forgot" initialValues={{ remember: true }} onFinish={onFinish} style={{ width: '100%', }}>
              <h2>Uma nova senha será enviada para seu email</h2>
              <Form.Item name="email" rules={[{ required: true, message: 'Entre com seu email.' }]}>
                <Input prefix={<MailOutlined className="site-form-item-icon" />} placeholder="Email..." />
              </Form.Item>
              <Form.Item>
                <Row>
                  <Button type="primary" htmlType="submit" style={{ width: '100%' }}>Enviar</Button>
                </Row>
              </Form.Item>
            </Form>
          </Row>
        </Col>
      </Row>
    </Fragment>
  )
}