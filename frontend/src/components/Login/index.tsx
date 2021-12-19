import { Button, Checkbox, Form, Input, Row } from "antd";
import { MailOutlined, LockOutlined } from '@ant-design/icons';

interface iLogin {
  // onFinish(event: React.ChangeEvent<HTMLInputElement>): Promise<void>;
  onFinish(event: any): any;
  onForgot(event: any): any;
  onSignUp(event: any): any;
}

export const LoginComponent: React.FC<iLogin> = ({ onFinish, onForgot, onSignUp }) => {

  const [form] = Form.useForm();

  return (
    <Form name="login" form={form} initialValues={{ remember: true }} onFinish={onFinish} autoComplete="off" style={{ width: '100%', }}>
      <Form.Item name="email" rules={[{ required: true, message: 'Entre com seu email.' }]}>
        <Input prefix={<MailOutlined className="site-form-item-icon" />} placeholder="Email..." />
      </Form.Item>
      <Form.Item name="password" rules={[{ required: true, message: 'Entre com sua senha.' }]}>
        <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} placeholder="Senha..." />
      </Form.Item>
      <Form.Item name="remember" valuePropName="checked" noStyle>
        <Checkbox>Lembre de mim</Checkbox>
      </Form.Item>
      <Form.Item style={{ paddingTop: '10px' }}>
        <Button type="link" style={{ float: 'left' }} onClick={onForgot} > Esqueci a senha </Button>
        <Button type="link" style={{ float: 'right' }} onClick={onSignUp} > Registre-me agora </Button>
      </Form.Item>
      <Form.Item>
        <Row>
          <Button type="primary" htmlType="submit" style={{ width: '100%' }}> Entrar </Button>
        </Row>
      </Form.Item>
    </Form>
  )
}