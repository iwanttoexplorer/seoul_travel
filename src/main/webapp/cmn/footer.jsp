<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Footer Example</title>
    <style>
        .footer {
            background-color: #f8f9fa; /* 통일된 색상 */
            padding: 1rem 0;
            text-align: center;
            border-top: 1px solid #dee2e6;
        }
        .footer .container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .footer .brand {
            font-size: 1.5rem;
            color: #000; /* 통일된 색상 */
        }
        .footer .text-muted {
            margin: 0;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <footer class="footer">
        <div class="container">
            <div class="brand">SEOUL TRAVEL</div>
            <p class="text-muted">&copy; 2024 3Team SEOUL_TRAVEL, Inc</p>
        </div>
    </footer>
</body>
</html>