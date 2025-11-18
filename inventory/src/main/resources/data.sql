INSERT INTO ingredients (name, category, quantity, unit, reorder_level)
SELECT * FROM (SELECT 'Tomatoes', 'Produce', 50, 'kg', 10) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM ingredients WHERE name = 'Tomatoes') LIMIT 1;

INSERT INTO ingredients (name, category, quantity, unit, reorder_level)
SELECT * FROM (SELECT 'Mozzarella', 'Dairy', 20, 'kg', 5) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM ingredients WHERE name = 'Mozzarella') LIMIT 1;

INSERT INTO ingredients (name, category, quantity, unit, reorder_level)
SELECT * FROM (SELECT 'Olive Oil', 'Condiment', 15, 'liters', 5) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM ingredients WHERE name = 'Olive Oil') LIMIT 1;

INSERT INTO ingredients (name, category, quantity, unit, reorder_level)
SELECT * FROM (SELECT 'Flour', 'Baking', 40, 'kg', 10) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM ingredients WHERE name = 'Flour') LIMIT 1;

INSERT INTO ingredients (name, category, quantity, unit, reorder_level)
SELECT * FROM (SELECT 'Basil', 'Herb', 5, 'bunches', 2) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM ingredients WHERE name = 'Basil') LIMIT 1;

INSERT INTO ingredients (name, category, quantity, unit, reorder_level)
SELECT * FROM (SELECT 'Salt', 'Seasoning', 25, 'kg', 5) AS tmp
WHERE NOT EXISTS (SELECT 1 FROM ingredients WHERE name = 'Salt') LIMIT 1;
