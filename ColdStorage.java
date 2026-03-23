import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColdStorage {

    // =====================================================================
    // SHARED COLORS
    // =====================================================================
    static final Color BG = new Color(8, 20, 50);
    static final Color SIDEBAR = new Color(5, 15, 40);
    static final Color CARD = new Color(0, 25, 65);
    static final Color CYAN = new Color(0, 210, 255);
    static final Color GOLD = new Color(255, 200, 50);
    static final Color GREEN = new Color(0, 220, 130);
    static final Color RED = new Color(255, 80, 100);
    static final Color TEXT = new Color(200, 230, 255);
    static final Color MUTED = new Color(80, 130, 180);
    static final Color BORDER = new Color(0, 80, 140);

    // =====================================================================
    // ENTRY POINT
    // =====================================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showLogin());
    }

    // =====================================================================
    // LOGIN SCREEN
    // =====================================================================
    static void showLogin() {
        JFrame frame = new JFrame("ColdVault - Admin Login");
        frame.setSize(420, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel main = new JPanel();
        main.setBackground(BG);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Logo
        JLabel logo = new JLabel("❄");
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 55));
        logo.setForeground(CYAN);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(logo);

        main.add(Box.createVerticalStrut(5));

        JLabel appName = new JLabel("COLDVAULT");
        appName.setFont(new Font("Impact", Font.PLAIN, 34));
        appName.setForeground(new Color(180, 240, 255));
        appName.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(appName);

        JLabel appSub = new JLabel("Cold Storage Management System");
        appSub.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        appSub.setForeground(MUTED);
        appSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(appSub);

        main.add(Box.createVerticalStrut(15));

        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(BORDER);
        main.add(sep);

        main.add(Box.createVerticalStrut(15));

        JLabel badge = new JLabel("🔐  Admin Login Portal");
        badge.setFont(new Font("Segoe UI", Font.BOLD, 14));
        badge.setForeground(GOLD);
        badge.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(badge);

        main.add(Box.createVerticalStrut(20));

        // Username
        JLabel uLbl = new JLabel("👤  Username");
        uLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        uLbl.setForeground(TEXT);
        uLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        main.add(uLbl);
        main.add(Box.createVerticalStrut(5));

        JTextField userField = new JTextField();
        userField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userField.setBackground(new Color(0, 25, 60));
        userField.setForeground(Color.WHITE);
        userField.setCaretColor(Color.CYAN);
        userField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 150, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        main.add(userField);

        main.add(Box.createVerticalStrut(14));

        // Password
        JLabel pLbl = new JLabel("🔑  Password");
        pLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        pLbl.setForeground(TEXT);
        pLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        main.add(pLbl);
        main.add(Box.createVerticalStrut(5));

        JPasswordField passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passField.setBackground(new Color(0, 25, 60));
        passField.setForeground(Color.WHITE);
        passField.setCaretColor(Color.CYAN);
        passField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 150, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        main.add(passField);

        main.add(Box.createVerticalStrut(8));

        JCheckBox showPw = new JCheckBox("👁  Show Password");
        showPw.setOpaque(false);
        showPw.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        showPw.setForeground(MUTED);
        showPw.setAlignmentX(Component.LEFT_ALIGNMENT);
        showPw.addActionListener(e -> passField.setEchoChar(showPw.isSelected() ? (char) 0 : '*'));
        main.add(showPw);

        main.add(Box.createVerticalStrut(20));

        // Login button
        JButton loginBtn = new JButton("🚀  LOGIN");
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginBtn.setBackground(new Color(0, 160, 220));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // *** THE FIX: lambda closes login frame, opens dashboard ***
        ActionListener doLogin = e -> {
            String user = userField.getText().trim();
            String pass = new String(passField.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "⚠  Please fill all fields!", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else if (user.equals("admin") && pass.equals("admin123")) {
                frame.dispose(); // close login
                showDashboard(user); // open dashboard
            } else {
                JOptionPane.showMessageDialog(frame,
                        "❌  Wrong credentials!\nUse:  admin / admin123", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        };

        loginBtn.addActionListener(doLogin);
        userField.addActionListener(doLogin); // Enter key works too
        passField.addActionListener(doLogin);

        main.add(loginBtn);

        main.add(Box.createVerticalStrut(20));

        JLabel footer = new JLabel("🌡 Temp  •  📦 Inventory  •  🔒 Secured");
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        footer.setForeground(new Color(60, 110, 150));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(footer);

        frame.setContentPane(main);
        frame.setVisible(true);
    }

    // =====================================================================
    // DASHBOARD SCREEN
    // =====================================================================
    static void showDashboard(String adminName) {
        JFrame frame = new JFrame("ColdVault — Dashboard");
        frame.setSize(1050, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(BG);

        // ── Top Bar ──
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(SIDEBAR);
        topBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER),
                BorderFactory.createEmptyBorder(12, 20, 12, 20)));

        JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        topLeft.setOpaque(false);
        JLabel tIco = new JLabel("❄");
        tIco.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        tIco.setForeground(CYAN);
        JLabel tName = new JLabel("COLDVAULT");
        tName.setFont(new Font("Impact", Font.PLAIN, 22));
        tName.setForeground(new Color(180, 240, 255));
        topLeft.add(tIco);
        topLeft.add(tName);

        JPanel topRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        topRight.setOpaque(false);
        JLabel adminLbl = new JLabel("👤  " + adminName.toUpperCase());
        adminLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        adminLbl.setForeground(GOLD);
        JButton logoutBtn = new JButton("🔓 Logout");
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        logoutBtn.setBackground(RED);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setOpaque(true);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.addActionListener(e -> {
            int r = JOptionPane.showConfirmDialog(frame,
                    "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                frame.dispose();
                showLogin();
            }
        });
        topRight.add(adminLbl);
        topRight.add(logoutBtn);
        topBar.add(topLeft, BorderLayout.WEST);
        topBar.add(topRight, BorderLayout.EAST);
        frame.add(topBar, BorderLayout.NORTH);

        // ── Sidebar ──
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(190, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER));

        String[][] navItems = {
                { "🏠", " Dashboard" }, { "📦", " Inventory" },
                { "🌡", " Temperature" }, { "🚚", " Deliveries" },
                { "👥", " Customers" }, { "📊", " Reports" }, { "⚙", " Settings" }
        };
        for (int i = 0; i < navItems.length; i++) {
            boolean active = (i == 0);
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 10));
            row.setOpaque(true);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
            row.setBackground(active ? new Color(0, 80, 150) : SIDEBAR);
            JLabel ico2 = new JLabel(navItems[i][0]);
            ico2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
            ico2.setForeground(active ? Color.WHITE : MUTED);
            JLabel lbl2 = new JLabel(navItems[i][1]);
            lbl2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lbl2.setForeground(active ? Color.WHITE : MUTED);
            row.add(ico2);
            row.add(lbl2);
            row.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            row.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    if (!row.getBackground().equals(new Color(0, 80, 150))) {
                        row.setBackground(new Color(0, 40, 90));
                        lbl2.setForeground(CYAN);
                        ico2.setForeground(CYAN);
                    }
                }

                public void mouseExited(MouseEvent e) {
                    if (!row.getBackground().equals(new Color(0, 80, 150))) {
                        row.setBackground(SIDEBAR);
                        lbl2.setForeground(MUTED);
                        ico2.setForeground(MUTED);
                    }
                }
            });
            sidebar.add(row);
        }
        sidebar.add(Box.createVerticalGlue());
        JLabel verLbl = new JLabel("v1.0  •  ColdVault");
        verLbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        verLbl.setForeground(new Color(40, 80, 120));
        verLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(verLbl);
        sidebar.add(Box.createVerticalStrut(10));
        frame.add(sidebar, BorderLayout.WEST);

        // ── Main Content ──
        JPanel content = new JPanel();
        content.setBackground(BG);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel pg1 = new JLabel("📊  Dashboard Overview");
        pg1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        pg1.setForeground(new Color(180, 240, 255));
        pg1.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(pg1);

        JLabel pg2 = new JLabel("Welcome back, " + adminName + "! Here's today's overview.");
        pg2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        pg2.setForeground(MUTED);
        pg2.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(pg2);
        content.add(Box.createVerticalStrut(18));

        // Stat Cards
        JPanel cards = new JPanel(new GridLayout(1, 4, 14, 0));
        cards.setOpaque(false);
        cards.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        cards.setAlignmentX(Component.LEFT_ALIGNMENT);
        cards.add(statCard("📦  Total Items", "1,240", CYAN, "+12 today"));
        cards.add(statCard("🌡  Avg Temp", "-18°C", GREEN, "Normal ✅"));
        cards.add(statCard("🚚  Deliveries", "34", GOLD, "8 pending"));
        cards.add(statCard("⚠   Alerts", "3", RED, "Action needed!"));
        content.add(cards);
        content.add(Box.createVerticalStrut(18));

        // Bottom Row
        JPanel bottom = new JPanel(new GridLayout(1, 2, 14, 0));
        bottom.setOpaque(false);
        bottom.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottom.add(inventoryTable());
        bottom.add(statusPanel());
        content.add(bottom);

        frame.add(content, BorderLayout.CENTER);
        frame.setVisible(true); // <-- show dashboard
    }

    // ── Stat Card ──
    static JPanel statCard(String label, String value, Color accent, String sub) {
        JPanel c = new JPanel();
        c.setBackground(CARD);
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accent, 1),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)));
        JLabel l1 = new JLabel(label);
        l1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 12));
        l1.setForeground(MUTED);
        l1.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel l2 = new JLabel(value);
        l2.setFont(new Font("Impact", Font.PLAIN, 30));
        l2.setForeground(accent);
        l2.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel l3 = new JLabel(sub);
        l3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        l3.setForeground(MUTED);
        l3.setAlignmentX(Component.LEFT_ALIGNMENT);
        c.add(l1);
        c.add(Box.createVerticalStrut(6));
        c.add(l2);
        c.add(Box.createVerticalStrut(4));
        c.add(l3);
        return c;
    }

    // ── Inventory Table ──
    static JPanel inventoryTable() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(CARD);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        JLabel t = new JLabel("📦  Recent Inventory");
        t.setFont(new Font("Segoe UI", Font.BOLD, 14));
        t.setForeground(CYAN);
        t.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        p.add(t, BorderLayout.NORTH);

        String[] cols = { "Item", "Qty", "Temp", "Status" };
        Object[][] rows = {
                { "🥔 Potato", "3200 kg", "1-2°C", "✅ OK" },
                { "🍅 Tomato", "2100 kg", "10-15°C", "✅ OK" },
                { "🥕 Carrots", "1500 kg", "-2-2°C", "✅ OK" },
                { "🥦 Vegetables", "900 kg", "-3°C", "⚠ Low" },

        };
        DefaultTableModel m = new DefaultTableModel(rows, cols) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        JTable table = new JTable(m);
        table.setBackground(CARD);
        table.setForeground(TEXT);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(26);
        table.setGridColor(BORDER);
        table.setSelectionBackground(new Color(0, 80, 150));
        table.setSelectionForeground(Color.WHITE);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.getTableHeader().setBackground(new Color(0, 40, 90));
        table.getTableHeader().setForeground(CYAN);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        JScrollPane sc = new JScrollPane(table);
        sc.setBorder(BorderFactory.createEmptyBorder());
        sc.getViewport().setBackground(CARD);
        p.add(sc, BorderLayout.CENTER);
        return p;
    }

    // ── Status Panel ──
    static JPanel statusPanel() {
        JPanel p = new JPanel();
        p.setBackground(CARD);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        JLabel t = new JLabel("🌡  Chamber Status");
        t.setFont(new Font("Segoe UI", Font.BOLD, 14));
        t.setForeground(CYAN);
        t.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(t);
        p.add(Box.createVerticalStrut(12));

        String[][] ch = {
                { "Chamber A", "-18°C", "92%", "🟢 Active" },
                { "Chamber B", "-20°C", "87%", "🟢 Active" },
                { "Chamber C", "-5°C", "45%", "🟡 Low Cap" },
                { "Chamber D", "-22°C", "100%", "🔴 Full" },
        };
        for (String[] r : ch) {
            JPanel row = new JPanel(new GridLayout(1, 4, 6, 0));
            row.setOpaque(false);
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
            row.setAlignmentX(Component.LEFT_ALIGNMENT);
            row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
            row.add(cell(r[0], TEXT, Font.BOLD));
            row.add(cell(r[1], CYAN, Font.PLAIN));
            row.add(cell(r[2], GOLD, Font.PLAIN));
            row.add(cell(r[3], MUTED, Font.PLAIN));
            p.add(row);
            p.add(Box.createVerticalStrut(4));
        }

        p.add(Box.createVerticalStrut(14));
        JLabel qa = new JLabel("⚡  Quick Actions");
        qa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        qa.setForeground(GOLD);
        qa.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(qa);
        p.add(Box.createVerticalStrut(10));

        addBtn(p, "➕  Add Item", new Color(0, 140, 100));
        addBtn(p, "📋  View Reports", new Color(0, 100, 180));
        addBtn(p, "🔔  View Alerts", new Color(180, 80, 0));
        addBtn(p, "🚚  New Delivery", new Color(100, 0, 160));
        return p;
    }

    static void addBtn(JPanel parent, String text, Color bg) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.add(b);
        parent.add(Box.createVerticalStrut(6));
    }

    static JLabel cell(String text, Color color, int style) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", style, 12));
        l.setForeground(color);
        return l;
    }
}
