import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class AdminLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox showPassword;

    // Color Palette
    private final Color BG_TOP        = new Color(0, 18, 42);
    private final Color BG_BOTTOM     = new Color(0, 40, 80);
    private final Color PANEL_BG      = new Color(5, 25, 55, 220);
    private final Color ACCENT_CYAN   = new Color(0, 210, 255);
    private final Color ACCENT_ICE    = new Color(180, 240, 255);
    private final Color ACCENT_GOLD   = new Color(255, 200, 50);
    private final Color TEXT_WHITE    = new Color(230, 245, 255);
    private final Color TEXT_MUTED    = new Color(100, 160, 200);
    private final Color FIELD_BG      = new Color(0, 30, 65);
    private final Color FIELD_BORDER  = new Color(0, 160, 210);
    private final Color BTN_FROM      = new Color(0, 180, 230);
    private final Color BTN_TO        = new Color(0, 100, 180);
    private final Color ERROR_RED     = new Color(255, 80, 100);

    public AdminLogin() {
        setTitle("ColdVault — Admin Portal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 640);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(new GradientPanel());
        setLayout(new GridBagLayout());

        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 40, 0, 40);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // ── Snowflake Icon ──────────────────────────────────────────
        gbc.gridy = 0;
        gbc.insets = new Insets(36, 40, 0, 40);
        JLabel snowflake = new JLabel("❄", SwingConstants.CENTER);
        snowflake.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        snowflake.setForeground(ACCENT_CYAN);
        add(snowflake, gbc);

        // ── App Name ────────────────────────────────────────────────
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 40, 0, 40);
        JLabel appName = new JLabel("COLDVAULT", SwingConstants.CENTER);
        appName.setFont(new Font("Impact", Font.PLAIN, 38));
        appName.setForeground(ACCENT_ICE);
        add(appName, gbc);

        // ── Subtitle ────────────────────────────────────────────────
        gbc.gridy = 2;
        gbc.insets = new Insets(2, 40, 0, 40);
        JLabel subtitle = new JLabel("❄  Cold Storage Management System  ❄", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        subtitle.setForeground(TEXT_MUTED);
        add(subtitle, gbc);

        // ── Divider ─────────────────────────────────────────────────
        gbc.gridy = 3;
        gbc.insets = new Insets(14, 40, 14, 40);
        add(makeDivider(), gbc);

        // ── Admin Badge ─────────────────────────────────────────────
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 40, 20, 40);
        JLabel badge = new JLabel("🔐  ADMIN LOGIN PORTAL", SwingConstants.CENTER);
        badge.setFont(new Font("Segoe UI", Font.BOLD, 14));
        badge.setForeground(ACCENT_GOLD);
        add(badge, gbc);

        // ── Username Label ──────────────────────────────────────────
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 48, 4, 48);
        JLabel userLabel = makeLabel("👤  Username");
        add(userLabel, gbc);

        // ── Username Field ──────────────────────────────────────────
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 48, 14, 48);
        usernameField = makeTextField("Enter admin username");
        add(usernameField, gbc);

        // ── Password Label ──────────────────────────────────────────
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 48, 4, 48);
        JLabel passLabel = makeLabel("🔑  Password");
        add(passLabel, gbc);

        // ── Password Field ──────────────────────────────────────────
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 48, 8, 48);
        passwordField = makePasswordField("Enter password");
        add(passwordField, gbc);

        // ── Show Password ────────────────────────────────────────────
        gbc.gridy = 9;
        gbc.insets = new Insets(0, 48, 20, 48);
        showPassword = new JCheckBox("👁  Show Password");
        showPassword.setOpaque(false);
        showPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        showPassword.setForeground(TEXT_MUTED);
        showPassword.setFocusPainted(false);
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected())
                passwordField.setEchoChar((char) 0);
            else
                passwordField.setEchoChar('●');
        });
        add(showPassword, gbc);

        // ── Login Button ─────────────────────────────────────────────
        gbc.gridy = 10;
        gbc.insets = new Insets(0, 48, 12, 48);
        loginButton = makeLoginButton();
        add(loginButton, gbc);

        // ── Forgot Password ──────────────────────────────────────────
        gbc.gridy = 11;
        gbc.insets = new Insets(0, 48, 30, 48);
        JLabel forgot = new JLabel("🔓  Forgot Password? Contact IT Support", SwingConstants.CENTER);
        forgot.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        forgot.setForeground(TEXT_MUTED);
        forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgot.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { forgot.setForeground(ACCENT_CYAN); }
            public void mouseExited(MouseEvent e)  { forgot.setForeground(TEXT_MUTED); }
        });
        add(forgot, gbc);

        // ── Footer ───────────────────────────────────────────────────
        gbc.gridy = 12;
        gbc.insets = new Insets(0, 40, 14, 40);
        JLabel footer = new JLabel("🌡  Temperature Monitored  •  📦  Inventory Tracked  •  🔒  Secured", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        footer.setForeground(new Color(60, 110, 150));
        add(footer, gbc);
    }

    // ── Helpers ───────────────────────────────────────────────────────

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(ACCENT_ICE);
        return lbl;
    }

    private JTextField makeTextField(String placeholder) {
        JTextField field = new JTextField() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(FIELD_BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                if (getText().isEmpty() && !isFocusOwner()) {
                    g2.setColor(TEXT_MUTED);
                    g2.setFont(new Font("Segoe UI", Font.ITALIC, 13));
                    g2.drawString(placeholder, 12, getHeight() / 2 + 5);
                }
                g2.dispose();
            }
        };
        styleField(field);
        return field;
    }

    private JPasswordField makePasswordField(String placeholder) {
        JPasswordField field = new JPasswordField() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(FIELD_BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                if (getPassword().length == 0 && !isFocusOwner()) {
                    g2.setColor(TEXT_MUTED);
                    g2.setFont(new Font("Segoe UI", Font.ITALIC, 13));
                    g2.drawString(placeholder, 12, getHeight() / 2 + 5);
                }
                g2.dispose();
            }
        };
        field.setEchoChar('●');
        styleField(field);
        return field;
    }

    private void styleField(JTextField field) {
        field.setOpaque(false);
        field.setForeground(TEXT_WHITE);
        field.setCaretColor(ACCENT_CYAN);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(12, FIELD_BORDER, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        field.setPreferredSize(new Dimension(0, 44));
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    new RoundedBorder(12, ACCENT_CYAN, 2),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    new RoundedBorder(12, FIELD_BORDER, 1),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)
                ));
            }
        });
    }

    private JButton makeLoginButton() {
        JButton btn = new JButton("🚀  LOGIN TO DASHBOARD") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = getModel().isPressed()
                    ? new GradientPaint(0, 0, BTN_TO, getWidth(), getHeight(), BTN_FROM)
                    : new GradientPaint(0, 0, BTN_FROM, getWidth(), getHeight(), BTN_TO);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                // Shine
                g2.setColor(new Color(255, 255, 255, 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight() / 2, 14, 14);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setPreferredSize(new Dimension(0, 48));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(ACCENT_GOLD); }
            public void mouseExited(MouseEvent e)  { btn.setForeground(Color.WHITE); }
        });
        btn.addActionListener(e -> handleLogin());
        return btn;
    }

    private JSeparator makeDivider() {
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(0, 100, 150, 120));
        sep.setBackground(new Color(0, 100, 150, 120));
        return sep;
    }

    private void handleLogin() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            showMessage("⚠  Please fill in all fields!", ERROR_RED);
            return;
        }

        // Demo credentials
        if (user.equals("admin") && pass.equals("admin123")) {
            showMessage("✅  Login Successful! Welcome, " + user + "!", new Color(0, 220, 130));
        } else {
            showMessage("❌  Invalid credentials. Access Denied!", ERROR_RED);
        }
    }

    private void showMessage(String msg, Color color) {
        JOptionPane pane = new JOptionPane(msg, JOptionPane.PLAIN_MESSAGE);
        JDialog dialog = pane.createDialog(this, "ColdVault System");
        dialog.setVisible(true);
    }

    // ── Gradient Background Panel ────────────────────────────────────
    class GradientPanel extends JPanel {
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Background gradient
            GradientPaint bg = new GradientPaint(0, 0, BG_TOP, 0, getHeight(), BG_BOTTOM);
            g2.setPaint(bg);
            g2.fillRect(0, 0, getWidth(), getHeight());

            // Decorative frost circles
            g2.setColor(new Color(0, 180, 255, 18));
            g2.fillOval(-80, -80, 300, 300);
            g2.fillOval(getWidth() - 180, getHeight() - 200, 320, 320);
            g2.setColor(new Color(0, 120, 200, 12));
            g2.fillOval(getWidth() / 2 - 100, getHeight() / 2 - 100, 200, 200);

            // Center card panel
            g2.setColor(PANEL_BG);
            int margin = 30, arc = 24;
            g2.fillRoundRect(margin, margin, getWidth() - margin * 2, getHeight() - margin * 2, arc, arc);
            g2.setColor(new Color(0, 160, 220, 60));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(margin, margin, getWidth() - margin * 2, getHeight() - margin * 2, arc, arc);
        }
    }

    // ── Rounded Border ───────────────────────────────────────────────
    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color color;
        private final int thickness;

        RoundedBorder(int radius, Color color, int thickness) {
            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
        }

        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x, y, w - 1, h - 1, radius, radius);
            g2.dispose();
        }

        @Override public Insets getBorderInsets(Component c) { return new Insets(4, 4, 4, 4); }
        @Override public Insets getBorderInsets(Component c, Insets i) {
            i.set(4, 4, 4, 4); return i;
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}
        SwingUtilities.invokeLater(AdminLogin::new);
    }
}