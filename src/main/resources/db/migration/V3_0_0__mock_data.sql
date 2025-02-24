INSERT INTO public.users (id, balance, created_at, name,version)
VALUES
    (1, 10000.00, '2025-02-24 09:24:23.354777', 'Javohir',0),
    (2, 20000.00, '2025-02-24 09:24:31.801131', 'Shohruh',0),
    (3, 30000.00, '2025-02-24 09:24:40.261280', 'Olmos',0),
    (4, 40000.00, '2025-02-24 09:24:53.040888', 'Evgeniy',0)
    ON CONFLICT (id)
DO UPDATE SET
    balance = EXCLUDED.balance,
           created_at = EXCLUDED.created_at,
           name = EXCLUDED.name;


INSERT INTO public.transaction (request_id, amount, created_at, remaining, type, user_id)
VALUES
    ('3fa85f64-5717-4562-b3fc-2c963f66afa6', 50.00, '2025-02-24 09:27:16.789738', 9950.00, 'WITHDRAW', 1),
    ('2fa85f64-5717-4562-b3fc-2c963f66afa6', 150.00, '2025-02-24 09:27:23.689075', 19850.00, 'WITHDRAW', 2),
    ('2fa85f65-5717-4562-b3fc-2c963f66afa6', 75.00, '2025-02-24 09:27:36.305692', 29925.00, 'WITHDRAW', 3),
    ('2fa85f65-5717-4562-b4fc-2c963f66afa6', 90.00, '2025-02-24 09:27:46.185525', 39910.00, 'WITHDRAW', 4),
    ('3fa85f64-6717-4562-b3fc-2c963f66afa6', 100.00, '2025-02-24 09:28:17.300064', 10050.00, 'DEPOSIT', 1),
    ('3fa85f64-7717-4562-b3fc-2c963f66afa6', 300.00, '2025-02-24 09:28:38.402706', 30225.00, 'DEPOSIT', 3),
    ('3fa75f64-7717-4562-b3fc-2c963f66afa6', 234.00, '2025-02-24 09:28:49.175956', 40144.00, 'DEPOSIT', 4)
ON CONFLICT (request_id)
    DO UPDATE SET
                  amount = EXCLUDED.amount,
                  remaining = EXCLUDED.remaining,
                  type = EXCLUDED.type,
                  created_at = EXCLUDED.created_at;

