import { Button, Checkbox, Form, Input, Row } from "antd";
import { MailOutlined, LockOutlined, UserAddOutlined } from '@ant-design/icons';

interface iProfile {
  onFinish(event: any): any;
}

export const ProfileComponent: React.FC<iProfile> = ({ onFinish }) => {

  const [form] = Form.useForm();

  return (
    <Form name="profile" form={form} onFinish={onFinish} style={{ width: '100%', }}>
      <Form.Item name="name" rules={[{ required: true, message: 'Entre com seu nome.' }]}>
        <Input prefix={<UserAddOutlined className="site-form-item-icon" />} placeholder="Nome..." />
      </Form.Item>
      <Form.Item name="email" rules={[{ type: "email", message: 'Entre com seu email.' }]}>
        <Input prefix={<MailOutlined className="site-form-item-icon" />} placeholder="Email..." />
      </Form.Item>
      <Form.Item name="password" hasFeedback rules={[{ required: true, message: 'Entre com sua senha.' } ]}>
        <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} placeholder="Senha..." />
      </Form.Item>
      <Form.Item name="confirmPassword" dependencies={['password']} hasFeedback
        rules={[{
          required: true,
          message: 'Confirme sua senha.'
        },
        ({ getFieldValue }) => ({
          validator(_, value) {
            if (!value || getFieldValue('password') === value) {
              return Promise.resolve();
            }
            return Promise.reject(new Error('As duas senhas que você digitou não coincidem!'));
          },
        }),
        ]}>
        <Input.Password prefix={<LockOutlined className="site-form-item-icon" />} placeholder="Confirmação da senha..." />
      </Form.Item>
      <Form.Item>
        <Row>
          <Button type="primary" htmlType="submit" style={{ width: '100%' }}>Salvar</Button>
        </Row>
      </Form.Item>
    </Form>
  )
}
