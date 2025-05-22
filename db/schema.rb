ActiveRecord::Schema.define(version: 2025_05_22) do

  create_table "users", force: :cascade do |t|
    t.uuid "user_id", null: false, primary_key: true
    t.string "first_name", limit: 50, null: false
    t.string "last_name", limit: 50, null: false
    t.string "email", null: false, unique: true
    t.string "password", limit: 20, null: false
    t.string "phone_number", limit: 20, null: false
    t.string "role", limit: 10, null: false
  end

  create_table "workspaces", force: :cascade do |t|
    t.uuid "workspace_id", null: false, primary_key: true
    t.string "name", limit: 100, null: false
    t.text "description"
    t.decimal "price_per_hour", precision: 10, scale: 2, null: false
    t.string "status", limit: 20, null: false
  end

  create_table "bookings", force: :cascade do |t|
    t.uuid "booking_id", null: false, primary_key: true
    t.uuid "workspace_id", null: false
    t.uuid "user_id", null: false
    t.datetime "start_time", null: false
    t.datetime "end_time", null: false
    t.decimal "total_price", precision: 10, scale: 2, null: false
    t.string "status", limit: 20, null: false

    t.foreign_key "workspace_id", "workspaces", column: "workspace_id", name: "fk_booking_workspace"
    t.foreign_key "user_id", "users", column: "user_id", name: "fk_booking_user"
  end

  create_table "guest_access", force: :cascade do |t|
    t.uuid "guest_access_id", null: false, primary_key: true
    t.uuid "user_id", null: false
    t.string "guest_name", limit: 100, null: false
    t.string "guest_email", null: false
    t.datetime "access_start_time", null: false
    t.datetime "access_end_time", null: false
    t.string "status", limit: 20, null: false

    t.foreign_key "user_id", "users", column: "user_id", name: "fk_guest_access_user"
  end

  create_table "parking_spaces", force: :cascade do |t|
    t.uuid "parking_space_id", null: false, primary_key: true
    t.string "space_number", limit: 10, null: false, unique: true
    t.string "status", limit: 20, null: false
    t.decimal "price_per_day", precision: 10, scale: 2, null: false
  end

  create_table "supply_orders", force: :cascade do |t|
    t.uuid "order_id", null: false, primary_key: true
    t.uuid "user_id", null: false
    t.text "items", null: false
    t.datetime "order_date", null: false
    t.string "status", limit: 20, null: false

    t.foreign_key "user_id", "users", column: "user_id", name: "fk_supply_order_user"
  end

  create_table "support_tickets", force: :cascade do |t|
    t.uuid "ticket_id", null: false, primary_key: true
    t.uuid "user_id", null: false
    t.string "subject", limit: 200, null: false
    t.text "description", null: false
    t.string "priority", limit: 10, null: false
    t.string "status", limit: 20, null: false
    t.datetime "creation_date", null: false

    t.foreign_key "user_id", "users", column: "user_id", name: "fk_support_ticket_user"
  end

  # ENUM check constraints (в реальной БД можно добавить через CHECK)
  # Пример для PostgreSQL:
  #
  # execute <<-SQL
  #   ALTER TABLE users ADD CONSTRAINT user_role_check
  #     CHECK (role IN ('USER', 'ADMIN'));
  #   ALTER TABLE workspaces ADD CONSTRAINT workspace_status_check
  #     CHECK (status IN ('AVAILABLE', 'OCCUPIED', 'UNDER_MAINTENANCE'));
  #   ...
  # END

end